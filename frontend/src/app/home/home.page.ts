import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Libro } from '../models/libro';
import { LibrosService } from '../services/libros.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit{

  public libros: Array<Libro> = [];
  public milibro: Libro;

  constructor(private router: Router, private libroService: LibrosService) {}
  
  ngOnInit(): void {
    this.loadInfo();
  }

  loadInfo() {
    this.libroService.getLibros().subscribe((b: Array<Libro>) =>{
      this.libros = b;
    })
  }

  goToForm(){
    this.router.navigateByUrl("/form");
  }

  deleteLibro(idLibro: number){
    this.libroService.deleteLibro(idLibro).subscribe(() => {
      this.loadInfo();
    });
  }
}
