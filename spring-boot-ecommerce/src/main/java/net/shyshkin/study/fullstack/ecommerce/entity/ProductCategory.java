package net.shyshkin.study.fullstack.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "productCategory",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Product> products;

//  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
//  `category_name` VARCHAR(255) NULL DEFAULT NULL,

}
