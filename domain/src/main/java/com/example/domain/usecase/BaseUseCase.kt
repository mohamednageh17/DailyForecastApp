package com.example.domain.usecase

interface BaseUseCase<in Parameter, out Result> {

    suspend operator fun invoke(params: Parameter): Result
}