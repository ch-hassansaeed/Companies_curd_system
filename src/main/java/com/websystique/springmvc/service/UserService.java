package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Company;



public interface UserService {
	
	Company findById(long id);
	
	Company findByName(String name);
	
	void saveCompany(Company company);
	
	void updateCompany(Company company);
	
	void deleteCompanyById(long id);

	List<Company> findAllCompanies(); 
	
	void deleteAllCompanies();
	
	public boolean isCompanyExist(Company company);
	
}
