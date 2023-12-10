package br.com.moneybucket.service.category

import br.com.moneybucket.entity.Category
import br.com.moneybucket.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetCategoriesService(
    @Autowired
    private val categoryRepository: CategoryRepository
) {
    fun invoke(username: String): List<Category> {
        return categoryRepository.findByUsername(username)
    }
}