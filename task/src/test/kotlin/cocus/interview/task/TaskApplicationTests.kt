package cocus.interview.task

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext



@SpringBootTest
class TaskApplicationTests(
	@Autowired
	val ctx: WebApplicationContext
) {

	@Test
	fun contextLoads() {
	}

	lateinit var mock :MockMvc

	@BeforeEach
	fun configureMockMVC() {
		mock = MockMvcBuilders.webAppContextSetup(ctx).build()
	}

	@Test
	fun getAllUserRepositories_OkRequest() {
		mock.perform(get("/palbp").header("Accept", "application/json"))
			.andDo(print())
			.andExpect(status().isOk)
			.andExpect(content().json("{\"githubRepoList\":[{\"name\":\"laboratory\",\"owner\":{\"login\":\"palbp\"},\"branches\":[{\"name\":\"main\",\"commit\":{\"sha\":\"ce28b2763f80e77fc7bd2086c5f7a7c1a8de6876\"}}],\"fork\":false},{\"name\":\"sempre_a_codar\",\"owner\":{\"login\":\"palbp\"},\"branches\":[{\"name\":\"DRAG_MoveReviewScreen\",\"commit\":{\"sha\":\"46980481796734442a557de50a750ae3145b86c9\"}},{\"name\":\"DRAG_NewDrawingScreen\",\"commit\":{\"sha\":\"dca41898dd624bd2f2041d21a09ac5cbf73b73bf\"}},{\"name\":\"DRAG_playground\",\"commit\":{\"sha\":\"ed3e3d00dc91bb0357f513fe4f5d8afde64fbd2c\"}},{\"name\":\"DRAG#2_DrawingScreen\",\"commit\":{\"sha\":\"54b95f6221d91e2a197267a880ae180380da037d\"}},{\"name\":\"master\",\"commit\":{\"sha\":\"a13802274b32e90b20c15ffe8d38629b0c84dbd4\"}}],\"fork\":false}]}"))
	}

	@Test
	fun getAllUserRepositories_BadRequest_Accept_application_xml() {
		mock.perform(get("/palbp").header("Accept", "application/xml"))
			.andDo(print())
			.andExpect(status().isNotAcceptable)
	}

	@Test
	fun getAllUserRepositories_BadRequest_UnknownUser() {
		mock.perform(get("/userNotKnownAbcdefSupposedToFail").header("Accept", "application/json")) //I'll be really surprised if there is a user with this name
			.andDo(print())
			.andExpect(status().isNotFound)
			.andExpect(content().string("{\"status\":404,\"message\":\"Username does not exist\"}"))

	}

	@Test
	fun getAllUserRepositories_BadRequest_UsernameHasNoReadableCharacters() {
		mock.perform(get("/   	").header("Accept", "application/json"))
			.andDo(print())
			.andExpect(status().isBadRequest)
			.andExpect(content().string("{\"status\":400,\"message\":\"Username has no readable characters\"}"))
	}

//	@Test
//	fun getAllUserRepositories_BadRequest_


}
