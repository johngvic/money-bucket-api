package br.com.moneybucket.service.category

import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class EditCategoryService(
    @Autowired
    private val categoryRepository: CategoryRepository
) {
    fun invoke(categoryId: UUID, username: String, name: String) {
        val entity =
            categoryRepository.findByIdAndUsername(categoryId, username) ?:
            throw ResourceNotFoundException("Category not found")

        entity.name = name
        categoryRepository.save(entity);
    }
}