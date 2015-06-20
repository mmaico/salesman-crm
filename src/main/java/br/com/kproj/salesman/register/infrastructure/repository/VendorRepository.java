package br.com.kproj.salesman.register.infrastructure.repository;


import br.com.kproj.salesman.register.infrastructure.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
