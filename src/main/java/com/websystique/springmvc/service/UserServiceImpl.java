package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.model.Company;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Company> companies;
	
	static{
		companies= populateDummyCompanies();
	}

	public List<Company> findAllCompanies() {
		return companies;
	}
	
	public Company findById(long id) {
		for(Company company : companies){
			if(company.getId() == id){
				return company;
			}
		}
		return null;
	}
	
	public Company findByName(String name) {
		for(Company company : companies){
			if(company.getcompanyName().equalsIgnoreCase(name)){
				return company;
			}
		}
		return null;
	}
	
	public void saveCompany(Company company) {
		company.setId(counter.incrementAndGet());
		companies.add(company);
	}

	public void updateCompany(Company company) {
		int index = companies.indexOf(company);
		companies.set(index, company);
	}

	public void deleteCompanyById(long id) {
		
		for (Iterator<Company> iterator = companies.iterator(); iterator.hasNext(); ) {
		    Company company = iterator.next();
		    if (company.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isCompanyExist(Company company) {
		return findByName(company.getcompanyName())!=null;
	}
	
	public void deleteAllCompanies(){
		companies.clear();
	}

	private static List<Company> populateDummyCompanies(){
		List<Company> companies = new ArrayList<Company>();
                companies.add(new Company(counter.incrementAndGet(),"Hassan-co","Hassan Saeed", "Street 786 Office 37","Islamabad","PK","321321", "hassan-co@mail.com"));
		companies.add(new Company(counter.incrementAndGet(),"Jhon-co","Jhon Smith", "Street 234 Office 34","Newyork","US","123456", "jhon-co@mail.com"));
		companies.add(new Company(counter.incrementAndGet(),"Sarah-co","Sarah Paul", "Street 78 Office 67","Londan","US","654321", "sarah-co@mail.com"));

		return companies;
	}

  

}
