package br.com.moneybucket.entity

import br.com.moneybucket.enums.transactions.Type
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.UuidGenerator
import java.math.BigDecimal
import java.util.Date
import java.util.UUID

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
data class Transaction(
    @Id
    @GeneratedValue
    @UuidGenerator
    val id: UUID,
    var title: String,
    @Enumerated(EnumType.STRING)
    var type: Type,
    var category: UUID,
    @Column(name = "finance_institution")
    var financeInstitution: UUID,
    var date: Date,
    var value: BigDecimal
)