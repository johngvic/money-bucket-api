package br.com.moneybucket.dto.categories.res

import br.com.moneybucket.entity.Category

data class GetCategoriesResponse(
    val data: List<Category>
)
