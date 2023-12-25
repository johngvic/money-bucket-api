package br.com.moneybucket.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue
    @UuidGenerator
    val id: UUID?,
    val username: String,
    var name: String
)
