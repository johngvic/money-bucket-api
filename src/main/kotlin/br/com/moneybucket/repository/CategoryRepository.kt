package br.com.moneybucket.repository

import br.com.moneybucket.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CategoryRepository : JpaRepository<Category, UUID> {
    fun findByUsername(user: String): List<Category>
    fun findByIdAndUsername(id: UUID, username: String): Category?
    fun deleteByIdAndUsername(id: UUID, username: String): Int
}