import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Material {
  id?: number;
  nombre: string;
  descripcion: string;
  tipo: string;
  precio: number;
  fechaCompra: string;   // yyyy-MM-dd
  fechaVenta?: string;   // yyyy-MM-dd | undefined
  estado: 'ACTIVO' | 'DISPONIBLE' | 'ASIGNADO';
  ciudad: { id: number };
}

export interface Ciudad {
  id: number;
  nombre: string;
  departamento: { id: number; nombre?: string };
}

@Injectable({ providedIn: 'root' })
export class MaterialesService {
  // Usamos el proxy: /api → http://localhost:9000
  private apiUrl = '/api/materiales';
  private ciudadesUrl = '/api/ciudades';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Material[]> {
    return this.http.get<Material[]>(this.apiUrl);
  }
  getByTipo(tipo: string): Observable<Material[]> {
    return this.http.get<Material[]>(`${this.apiUrl}/tipo/${tipo}`);
  }
  getByFecha(inicio: string, fin: string): Observable<Material[]> {
    return this.http.get<Material[]>(`${this.apiUrl}/fecha-compra?inicio=${inicio}&fin=${fin}`);
  }
  getByCiudad(ciudadId: number): Observable<Material[]> {
    return this.http.get<Material[]>(`${this.apiUrl}/ciudad/${ciudadId}`);
  }
  getByEstado(estado: string): Observable<Material[]> {
    return this.http.get<Material[]>(`${this.apiUrl}/estado/${estado}`);
  }
  create(material: Material): Observable<Material> {
    return this.http.post<Material>(this.apiUrl, material);
  }
  update(id: number, material: Material): Observable<Material> {
    return this.http.put<Material>(`${this.apiUrl}/${id}`, material);
  }
  getCiudades(): Observable<Ciudad[]> {
    return this.http.get<Ciudad[]>(this.ciudadesUrl);
  }
}
