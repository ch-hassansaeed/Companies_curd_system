'use strict';

App.controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
          var self = this;
          self.company={id:null,companyName:'',owner:'',address:'',city:'',country:'',phoneNum:'',email:''};
          self.companies=[];
              
          self.fetchAllCompanies = function(){
              UserService.fetchAllCompanies()
                  .then(
      					       function(d) {
      						        self.companies = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.createCompany = function(company){
              UserService.createCompany(company)
		              .then(
                      self.fetchAllCompanies, 
				              function(errResponse){
					               console.error('Error while creating Company.');
				              }	
                  );
          };

         self.updateCompany = function(company, id){
              UserService.updateCompany(company, id)
		              .then(
				              self.fetchAllCompanies, 
				              function(errResponse){
					               console.error('Error while updating Company.');
				              }	
                  );
          };

         self.deleteCompany = function(id){
              UserService.deleteCompany(id)
		              .then(
				              self.fetchAllCompanies, 
				              function(errResponse){
					               console.error('Error while deleting Company.');
				              }	
                  );
          };

          self.fetchAllCompanies();

          self.submit = function() {
              if(self.company.id==null){
                  console.log('Saving New Company', self.company);    
                  self.createCompany(self.company);
              }else{
                  self.updateCompany(self.company, self.company.id);
                  console.log('Company updated with id ', self.company.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.companies.length; i++){
                  if(self.companies[i].id == id) {
                     self.company = angular.copy(self.companies[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.company.id === id) {//clean form if the company to be deleted is shown there.
                 self.reset();
              }
              self.deleteCompany(id);
          };

          
          self.reset = function(){
              self.company={id:null,companyName:'',owner:'',address:'',city:'',country:'',phoneNum:'',email:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
