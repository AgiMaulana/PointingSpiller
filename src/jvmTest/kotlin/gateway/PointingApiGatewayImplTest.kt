package gateway

import api.PointingPokerApi
import api.providers.RetrofitProviderFactory
import api.response.PointingPokerResponse
import domain.PointingPoker
import domain.PointingPoker.AveragePoint.PointCounter
import domain.PointingPoker.Observer
import domain.PointingPoker.Player
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PointingApiGatewayImplTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var gateway: PointingApiGateway

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val api = RetrofitProviderFactory(mockWebServer.url("/"))
            .get()
            .create(PointingPokerApi::class.java)
        gateway = PointingApiGatewayImpl(api)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `when get session is success then parse json`() = runTest {
        val data = javaClass.classLoader?.getResourceAsStream("api_response.json")
            ?.bufferedReader(Charsets.UTF_8)
            ?.readText()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(data ?: "{}")
        mockWebServer.enqueue(response)

        val result = gateway.getSession("")

        // players
        assertEquals(Player("Bob", 1), result.players.component1())
        assertEquals(Player("Alice", 0), result.players.component2())
        assertEquals(Player("Andrew", 2), result.players.component3())
        assertEquals(Player("James", 3), result.players.component4())
        assertEquals(Player("David", 2), result.players.component5())
        // observers
        assertEquals(Observer("Mike"), result.observers.component1())
        // point details
        assertEquals(PointCounter(2, 2), result.averagePoint.pointCounter.component1())
        assertEquals(PointCounter(1, 1), result.averagePoint.pointCounter.component2())
        assertEquals(PointCounter(3, 1), result.averagePoint.pointCounter.component3())
        assertEquals(1.6, result.averagePoint.average)
    }
}