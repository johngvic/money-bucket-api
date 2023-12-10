package br.com.moneybucket.service.category

import br.com.moneybucket.entity.Category
import br.com.moneybucket.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetCategoryByIdService(
    @Autowired
    private val categoryRepository: CategoryRepository
) {
    fun invoke(categoryId: UUID, username: String): Category? {
        return categoryRepository.findByIdAndUsername(categoryId, username)
    }
}