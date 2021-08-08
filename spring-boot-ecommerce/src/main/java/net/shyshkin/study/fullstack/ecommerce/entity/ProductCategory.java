package net.shyshkin.study.fullstack.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1631454873952124853L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Product> products;

//  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
//  `category_name` VARCHAR(255) NULL DEFAULT NULL,

}
