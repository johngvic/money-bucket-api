package br.com.moneybucket.controller

import br.com.moneybucket.dto.categories.req.CreateCategoryRequest
import br.com.moneybucket.dto.categories.req.EditCategoryRequest
import br.com.moneybucket.dto.categories.res.CreateCategoryResponse
import br.com.moneybucket.dto.categories.res.GetCategoriesResponse
import br.com.moneybucket.entity.Category
import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.helpers.StringParser
import br.com.moneybucket.service.category.*
import br.com.moneybucket.service.user.TokenService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@Slf4j
@RestController
@RequestMapping("categories")
class CategoryController(
    @Autowired
    private val tokenService: TokenService,
    private val createCategoryService: CreateCategoryService,
    private val getCategoriesService: GetCategoriesService,
    private val getCategoryByIdService: GetCategoryByIdService,
    private val editCategoryService: EditCategoryService,
    private val deleteCategoryService: DeleteCategoryService
) {
    @PostMapping
    fun createCategory(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: CreateCategoryRequest
    ): ResponseEntity<CreateCategoryResponse> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val response = createCategoryService.invoke(username, request.name)
        return ResponseEntity(CreateCategoryResponse(response), HttpStatus.CREATED)
    }

    @GetMapping
    fun getCategories(
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<GetCategoriesResponse> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val response = getCategoriesService.invoke(username)
        return ResponseEntity(GetCategoriesResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    fun getCategoryById(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable categoryId: UUID
    ): ResponseEntity<Category> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val response = getCategoryByIdService.invoke(categoryId, username)

        if (response != null) {
            return ResponseEntity(response, HttpStatus.OK)
        }

        throw ResourceNotFoundException("Category not found");
    }

    @PutMapping("/{categoryId}")
    fun editCategory(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: EditCategoryRequest,
        @PathVariable categoryId: UUID
    ): ResponseEntity<Unit> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        editCategoryService.invoke(categoryId, username, request.name)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable categoryId: UUID
    ): ResponseEntity<Unit> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        deleteCategoryService.invoke(categoryId, username)
        return ResponseEntity(HttpStatus.OK)
    }
}