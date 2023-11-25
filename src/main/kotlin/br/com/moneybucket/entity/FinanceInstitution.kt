package br.com.moneybucket.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "finance_institutions")
data class FinanceInstitution(
    @Id
    val id: UUID,
    val username: String,
    val name: String
)
