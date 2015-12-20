package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;

public class SalePackageBuilder extends AbstractBuilder<SalePackage>  {

	public SalePackageBuilder() {
		this.entity = new SalePackage();
	}

	public SalePackageBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static SalePackageBuilder createPackage(Long id) {
		return new SalePackageBuilder(id);
	}

	public static SalePackageBuilder createPackage() {
		return new SalePackageBuilder();
	}
}
