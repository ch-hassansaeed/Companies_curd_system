'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllCompanies: function() {
					return $http.get('http://localhost:8080/Companies_curd_system/company/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching companys');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createCompany: function(company){
					return $http.post('http://localhost:8080/Companies_curd_system/company/', company)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating company');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateCompany: function(company, id){
					return $http.put('http://localhost:8080/Companies_curd_system/company/'+id, company)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating company');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteCompany: function(id){
					return $http.delete('http://localhost:8080/Companies_curd_system/company/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting company');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
