package esTanFacil.backend;



import esTanFacil.backend.controller.AuthControllerTest;
import esTanFacil.backend.controller.CDonationsControllerTest;
import esTanFacil.backend.controller.CNewsControllerTest;
import esTanFacil.backend.model.CDonationsTest;
import esTanFacil.backend.model.CKmTest;
import esTanFacil.backend.model.CNewsTest;
import esTanFacil.backend.model.RoleTest;
import esTanFacil.backend.model.UserTest;
import esTanFacil.backend.payload.request.LoginRequestTest;
import esTanFacil.backend.payload.request.SignupRequestTest;
import esTanFacil.backend.payload.response.JwtResponseTest;
import esTanFacil.backend.payload.response.MessageResponseTest;
import esTanFacil.backend.security.jwt.AuthEntryPointJwtTest;
import esTanFacil.backend.security.jwt.JwtUtilsTest;
import esTanFacil.backend.security.services.UserDetailsImplTest;
import esTanFacil.backend.security.services.UserDetailsServiceImplTest;
import esTanFacil.backend.service.CServiceDonationTest;
import esTanFacil.backend.service.CServiceKmTest;
import esTanFacil.backend.service.CServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@DisplayName("Backend Application Tests")
@SelectClasses({
		LoginRequestTest.class,
		SignupRequestTest.class,
		JwtResponseTest.class,
		MessageResponseTest.class,
		AuthEntryPointJwtTest.class,
		JwtUtilsTest.class,
		AuthControllerTest.class,
		CNewsControllerTest.class,
		CDonationsControllerTest.class,
		CDonationsTest.class,
		CKmTest.class,
		CNewsTest.class,
		RoleTest.class,
		UserTest.class,
		UserDetailsImplTest.class,
		UserDetailsServiceImplTest.class,
		CServiceDonationTest.class,
		CServiceKmTest.class,
		CServiceTest.class
})

public class BackendApplicationTests {
}
