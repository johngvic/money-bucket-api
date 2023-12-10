package br.com.moneybucket.service.category

import br.com.moneybucket.entity.Category
import br.com.moneybucket.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateCategoryService(
    @Autowired
    private val categoryRepository: CategoryRepository
) {
    fun invoke(user: String, name: String): UUID {
        val generatedId = UUID.randomUUID()
        categoryRepository.save(Category(generatedId, user, name))
        return generatedId;
    }
}