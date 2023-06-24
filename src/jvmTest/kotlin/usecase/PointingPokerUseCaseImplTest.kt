package usecase

import gateway.PointingApiGateway
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PointingPokerUseCaseImplTest {
    @RelaxedMockK
    private lateinit var pointingApiGateway: PointingApiGateway
    private lateinit var useCase: PointingPokerUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = PointingPokerUseCaseImpl(pointingApiGateway)
    }

    @Test
    fun `when invoke then invoke gateway`() = runTest {
        val url = "https://"

        useCase.invoke(url)

        coVerify { pointingApiGateway.getSession(url) }
    }
}