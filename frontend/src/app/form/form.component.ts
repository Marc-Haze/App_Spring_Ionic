import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup , Validators} from '@angular/forms';
import { Libro } from '../models/libro';
import { LibrosService } from '../services/libros.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss'],
})
export class FormComponent implements OnInit {

  myForm: FormGroup;
  submitted = false;
  constructor(private router: Router, private libroService: LibrosService, public formBuilder: FormBuilder) { }

  ngOnInit() {
    this.myForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      author: ['', [Validators.required]],
      year: [,[Validators.required]],
    })
  }

  goToHome(){
    this.router.navigateByUrl("/home");
  }
  get errorCtr() {
    return this.myForm.controls;
  }
  
  onSubmit() {
    this.submitted = true;
    if (!this.myForm.valid) {
      console.log('Todos los Campos son OBLIGATORIOS.')
      return false;
    } else {
      const libro: Libro = this.myForm.value;
      this.libroService.addLibro(libro).subscribe(() => {
        this.myForm.reset;
        this.libroService.getLibros();
        this.router.navigateByUrl("/home");
      });
      console.log(this.myForm.value)
    }
  }
}
