package br.com.moneybucket.service.category

import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class DeleteCategoryService(
    @Autowired
    private val categoryRepository: CategoryRepository
) {
    fun invoke(categoryId: UUID, username: String) {
        val response = categoryRepository
            .deleteByIdAndUsername(categoryId, username)

        if (response == 0) throw ResourceNotFoundException("Category not found")
    }
}