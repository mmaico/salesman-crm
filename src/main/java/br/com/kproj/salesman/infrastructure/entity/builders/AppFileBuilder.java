package br.com.kproj.salesman.infrastructure.entity.builders;

import br.com.kproj.salesman.infrastructure.entity.AppFile;



public class AppFileBuilder extends AbstractBuilder<AppFile> {

	private AppFile appfile = new AppFile();
	
	public AppFileBuilder withFile(byte[] file) {
		this.appfile.setFile(file);
		return this;
	}
	
	public AppFileBuilder withMimeType(String mimeType) {
		this.appfile.setMimeType(mimeType);
		return this;
	}
	
	public AppFileBuilder withOriginalName(String name) {
		this.appfile.setOriginalName(name);
		return this;
	}
	
	public AppFileBuilder withSize(Long size) {
		this.appfile.setSize(size);
		return this;
	}
	
	public AppFile build() {
		return this.appfile;
	}
	
}
