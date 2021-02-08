package uz.tdpu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.tdpu.entity.Images;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
}
