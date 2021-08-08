package net.shyshkin.study.fullstack.ecommerce.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String sku;
    private String name;
    private String description;
    @Column(columnDefinition = "decimal", precision = 13, scale = 2)
    private BigDecimal unitPrice;
    private String imageUrl;
    private Boolean active;
    private Integer unitsInStock;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private ProductCategory category;

//  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
//  `sku` VARCHAR(255) DEFAULT NULL,
//  `name` VARCHAR(255) DEFAULT NULL,
//  `description` VARCHAR(255) DEFAULT NULL,
//  `unit_price` DECIMAL(13,2) DEFAULT NULL,
//  `image_url` VARCHAR(255) DEFAULT NULL,
//  `active` BIT DEFAULT 1,
//  `units_in_stock` INT(11) DEFAULT NULL,
//   `date_created` DATETIME(6) DEFAULT NULL,
//  `last_updated` DATETIME(6) DEFAULT NULL,

}
