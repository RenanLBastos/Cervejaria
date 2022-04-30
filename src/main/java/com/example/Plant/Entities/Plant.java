//package com.example.Plant.Entities;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
////@Getter
////@Setter
////@Entity
////@Table(name = "PLANTS")
//public class Plant implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
//    @PrimaryKeyJoinColumn
//    private Long id;
//
//    @JsonInclude(value = JsonInclude.Include.NON_NULL)
//    @Column(name = "NAME")
//    private String name;
//
//    @JsonInclude(value = JsonInclude.Include.NON_NULL)
//    @Column(name = "QUANTITY")
//    private Integer quantity;
//
//    @JsonInclude(value = JsonInclude.Include.NON_NULL)
//    @Column(name = "WATERING_FREQUENCY")
//    private Integer wateringFrequency;
//
//    @JsonInclude(value = JsonInclude.Include.NON_NULL)
//    @Column(name = "HAS_FRUIT")
//    private Boolean hasFruit;
//
//    @JoinColumn(name = "complexo_ID")
//    private Complexo complexo;
//
//
//    @OneToOne
//    @JoinColumn(name = "complexo_2_ID")
//    private Complexo2 complexo2;
//}
