import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Material, MaterialesService } from '../../services/materiales.service';
import { Ciudad } from '../../services/materiales.service';

@Component({
  selector: 'app-materiales',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './materiales.component.html'
})
export class MaterialesComponent {
  materiales: Material[] = [];
  ciudades: Ciudad[] = [];
  editando = false;

  // Form de crear/editar
  material: Material = this.vacio();

  // Filtros
  filtros = {
    tipo: '',
    inicio: '',
    fin: '',
    ciudadId: '',
    estado: ''
  };

  mensaje = '';

  constructor(private api: MaterialesService) {}

  ngOnInit(): void {
    this.cargarTodos();
    this.cargarCiudades();
  }

  vacio(): Material {
    return {
      nombre: '',
      descripcion: '',
      tipo: '',
      precio: 0,
      fechaCompra: '',
      fechaVenta: undefined,
      estado: 'DISPONIBLE',
      ciudad: { id: 0 }
    };
  }

  // ---- Listado
  cargarTodos() {
    this.api.getAll().subscribe({
      next: (data) => this.materiales = data,
      error: (e) => this.msg('Error listando materiales', true)
    });
  }

  cargarCiudades() {
    this.api.getCiudades().subscribe({
      next: (data) => this.ciudades = data,
      error: () => this.msg('Error cargando ciudades', true)
    });
  }

  // ---- Filtros
  buscarPorTipo() {
    if (!this.filtros.tipo.trim()) return this.cargarTodos();
    this.api.getByTipo(this.filtros.tipo.trim()).subscribe({
      next: (d) => this.materiales = d,
      error: () => this.msg('No se pudo filtrar por tipo', true)
    });
  }

  buscarPorFecha() {
    const { inicio, fin } = this.filtros;
    if (!inicio || !fin) return this.msg('Debes enviar inicio y fin', true);
    if (inicio > fin)   return this.msg('La fecha de inicio no puede ser mayor a la final', true);

    this.api.getByFecha(inicio, fin).subscribe({
      next: (d) => this.materiales = d,
      error: () => this.msg('No se pudo filtrar por fechas', true)
    });
  }

  buscarPorCiudad() {
    const id = Number(this.filtros.ciudadId);
    if (!id) return this.msg('Ingresa un ID de ciudad válido', true);

    this.api.getByCiudad(id).subscribe({
      next: (d) => this.materiales = d,
      error: () => this.msg('No se pudo filtrar por ciudad', true)
    });
  }

  buscarPorEstado() {
    if (!this.filtros.estado) return this.cargarTodos();
    this.api.getByEstado(this.filtros.estado).subscribe({
      next: (d) => this.materiales = d,
      error: () => this.msg('No se pudo filtrar por estado', true)
    });
  }

  // ---- Crear / Editar
  editar(m: Material) {
    // Copia profunda y normaliza fechas (yyyy-MM-dd)
    this.material = JSON.parse(JSON.stringify(m));
    this.material.fechaCompra = (this.material.fechaCompra || '').toString().slice(0,10);
    if (this.material.fechaVenta) {
      this.material.fechaVenta = this.material.fechaVenta.toString().slice(0,10);
    }
    this.editando = true;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  guardar() {
    // Validaciones básicas en UI
    if (!this.material.nombre.trim()) return this.msg('El nombre es obligatorio', true);
    if (!this.material.tipo.trim())   return this.msg('El tipo es obligatorio', true);
    if (!this.material.precio || this.material.precio < 0) return this.msg('Precio inválido', true);
    if (!this.material.fechaCompra)   return this.msg('La fecha de compra es obligatoria', true);
    if (this.material.fechaVenta && this.material.fechaCompra > this.material.fechaVenta) {
      return this.msg('La fecha de compra no puede ser mayor a la de venta', true);
    }
    if (!this.material.ciudad?.id) return this.msg('Debe indicar ciudadId', true);

    // Decide crear o actualizar
    if (this.editando && this.material.id) {
      this.api.update(this.material.id, this.material).subscribe({
        next: () => { this.msg('Material actualizado'); this.reset(); this.cargarTodos(); },
        error: () => this.msg('No se pudo actualizar', true)
      });
    } else {
      this.api.create(this.material).subscribe({
        next: () => { this.msg('Material creado'); this.reset(); this.cargarTodos(); },
        error: () => this.msg('No se pudo crear', true)
      });
    }
  }

  reset() {
    this.material = this.vacio();
    this.editando = false;
  }

  msg(texto: string, error = false) {
    this.mensaje = texto;
    if (error) console.error(texto);
    setTimeout(() => this.mensaje = '', 3000);
  }
}
