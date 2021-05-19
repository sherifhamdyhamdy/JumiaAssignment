import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './customer';


const customerLocalUrl = 'http://localhost:8080/customers';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  

  getCustomer(): Observable<any> {
    return this.http.get<Customer[]>(
      customerLocalUrl);
  }

  getCustomerWithFilter(state:string,country:string,page:string): Observable<any> {
    
   
    let params = new HttpParams({
      fromObject : {
        state: state,
        page: page
      }
    });
        if (country!=undefined && country!=null && country!="" && country.trim.length==0)
              params = params.append('country', country);
    console.log(params.toString());
    return this.http.get<Customer[]>(customerLocalUrl,{params});
  }

}
