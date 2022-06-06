package com.joaovicttors.presentation.character_list

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.domain.BaseUseCase
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.presentation.MainCoroutineScope
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CharacterListViewModelTest {

    @get:Rule
    val mainCoroutineScope = MainCoroutineScope()

    private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    private val getCharacterList: BaseUseCase<Int, List<Character>> = mockk()

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun before() {
        viewModel = CharacterListViewModel(dispatcher, getCharacterList)
    }

    @Test
    fun `when getCharacterList returns an Error on viewState`() = runTest { ->
        val expectedLoading = false
        val expectedData = emptyList<Character>()
        val expectedErrorMessage = "Runtime Error"

        coEvery { getCharacterList(0) } returns Response.Error(expectedErrorMessage)

        viewModel.getCharacterList()

        assertEquals(expectedLoading, viewModel.viewState.value.isLoading)
        assertEquals(expectedData, viewModel.viewState.value.data)
        assertEquals(expectedErrorMessage, viewModel.viewState.value.errorMessage)
    }

    @Test
    fun `when getCharacterList returns an Success on viewState`() = runTest { ->
        val expectedLoading = false
        val expectedData = listOf<Character>(mockk(), mockk())
        val expectedErrorMessage = null

        coEvery { getCharacterList(0) } returns Response.Success(expectedData)

        viewModel.getCharacterList()

        assertEquals(expectedLoading, viewModel.viewState.value.isLoading)
        assertEquals(expectedData, viewModel.viewState.value.data)
        assertEquals(expectedErrorMessage, viewModel.viewState.value.errorMessage)
    }

    @Test
    fun `when getCharacterList returns Loading as true on viewState`() = runTest { ->
        val expectedLoading = true
        val expectedData = emptyList<Character>()
        val expectedErrorMessage = null

        coEvery { getCharacterList(0) } coAnswers { delay(timeMillis = 1); Response.Error("") }

        viewModel.getCharacterList()

        assertEquals(expectedLoading, viewModel.viewState.value.isLoading)
        assertEquals(expectedData, viewModel.viewState.value.data)
        assertEquals(expectedErrorMessage, viewModel.viewState.value.errorMessage)
    }
}