package com.eduramza.mybraziliexapp.ui.listcrypto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eduramza.mybraziliexapp.ManagedCoroutineScope
import com.eduramza.mybraziliexapp.TestScope
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.repository.PublicRepositoryImpl
import com.eduramza.mybraziliexapp.data.source.RemoteService
import com.eduramza.mybraziliexapp.ui.CurrenciesViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import java.io.File

/**
 * Unit test for the implementation of [CurrenciesViewModel]
 */
@ExperimentalCoroutinesApi
class ListCryptoViewModelTest{
    //Subject under the test
    private lateinit var listCryptoViewModel: CurrenciesViewModel

    //Fake Repository
    private lateinit var repositoryImpl : PublicRepositoryImpl

    // Set the main coroutines dispatcher for unit testing.
    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope: ManagedCoroutineScope = TestScope(testDispatcher)

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //For mock response in json file
    var type = object : TypeToken<Tickers>(){}.type

    val MOCK_EXAMPLE_RESPONSE = "mock_response/ticker_list_response"

    @Before
    fun setupViewModel(){
        Dispatchers.setMain(testDispatcher)
//        repositoryImpl = PublicRepositoryImpl(RemoteService().fakeRemote())
        listCryptoViewModel =
            CurrenciesViewModel(repositoryImpl)
    }

    @After
    fun after(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testListIsNotNull() = runBlockingTest{

        initMockRepository()

        launch(testCoroutineScope.coroutineContext) { listCryptoViewModel.getAllTickers() }
        val result = listCryptoViewModel.getTickerResponse()
        Assert.assertNotNull(result)
    }

    private fun initMockRepository() = runBlockingTest {
        val data = getJson(MOCK_EXAMPLE_RESPONSE)
        val model = Gson().fromJson<Tickers>(data, type)

//        every { repositoryImpl.getAllTickers()  } returns model
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    fun getJson(path : String) : String {
        // Load the JSON response
        val uri = this.javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

}