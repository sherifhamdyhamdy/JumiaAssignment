import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';
import { Smartphone } from './smartphone';
import { Customer } from './customer';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  smartphone: Smartphone[] = [];
  customers:Customer[] = [];
  stateFilter:string;
  countryFilter:string;
  columnDefs = [
    { field: 'name'},
    { field: 'country' },
    { field: 'state' },
    { field: 'countryCode'},
    { field: 'number'}
];

rowData:Customer[] = [];
  gridApi: any;
  gridColumnApi: any;
  noRowsTemplate:any;
  loadingTemplate:any;
  currentPage="1";
  //pager = {};
  //pager:any=JSON.parse('{"totalItems":150,"currentPage":1,"pageSize":10,"totalPages":15,"startPage":1,"endPage":10,"startIndex":0,"endIndex":9,"pages":[1,2,3,4,5,6,7,8,9,10]}')
  //pager:any=JSON.parse('{"pages":[1,2,3,4,5],"numberofPages":8}')
  pager:any;
  pageOfItems = [];
  constructor(private api: ApiService) {
    //this.getSmartphones();
    this.getCustomers();
    this.stateFilter='All';
    this.loadingTemplate =
      "<span class='ag-overlay-loading-center'>data is loading...</span>";
    this.noRowsTemplate =
      "<span>no rows to show</span>";
    
  }
  title = 'angular-jumia-app';
  
  getCustomers() {
    this.api.getCustomer()
    .subscribe(resp => {
      console.log(resp);
     // const keys = resp.headers.keys();
     this.customers = [];
      for (const data of resp.customerDtoList) {
        this.customers.push(data);
      }
      this.pager=resp.pager;
      //this.rowData=this.customers
      console.log(this.customers);
    });
  }

  onGridReady(params)
  {
    this.gridApi=params.api;
    this.gridColumnApi=params.columnApi;
    params.api.setRowData(this.customers);
  }

  onButtonPressed()
  {
    console.log("state="+this.stateFilter)
    console.log("countryFilter:",this.countryFilter)
    this.currentPage="1";
    this.api.getCustomerWithFilter(this.stateFilter,this.countryFilter,this.currentPage)
      .subscribe(resp => {
      console.log(resp);
      this.customers = [];
      for (const data of resp.customerDtoList) {
        this.customers.push(data);
      }
      this.pager=resp.pager;
      console.log(this.customers);
    });
  }


  onGoToPage(page:string)
  {
    console.log("page===",page)
    this.currentPage=page;
    this.api.getCustomerWithFilter(this.stateFilter,this.countryFilter,this.currentPage)
      .subscribe(resp => {
      console.log(resp);
      this.customers = [];
      for (const data of resp.customerDtoList) {
        this.customers.push(data);
      }
      this.pager=resp.pager;
      console.log(this.customers);
    });
  }
  

  
}
