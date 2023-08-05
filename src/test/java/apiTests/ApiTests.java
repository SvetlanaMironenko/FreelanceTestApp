package apiTests;

import api.freelance.ApiAuthController;
import api.freelance.ApiCommentController;
import api.freelance.ApiJobController;
import api.freelance.ApiUserController;
import api.freelance.entities.*;
import common.BaseTest;
import common.logger.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ApiTests extends BaseTest {

    @Test
    public void createUserApiTest() {
        RegisterUser newRegisterUser = getRandomRegisterUser();
        ApiAuthController apiAuthController = new ApiAuthController();

        boolean IsRegistered = apiAuthController.registerUser(newRegisterUser);

        Assert.assertTrue(IsRegistered,
                "Check that user was registered with message 'User registered successfully'");
    }

    @Test
    public void loginUserApiTest() {
        Logger.step("1. Register user (for next login)");
        RegisterUser newRegisterUser = getRandomRegisterUser();
        ApiAuthController apiAuthController = registerRandomUserByApi(newRegisterUser);

        Logger.step("2. Login by this new user");
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(newRegisterUser.getUsername());
        loginUser.setPassword(newRegisterUser.getPassword());

        apiAuthController.login(loginUser);

        Assert.assertNotNull(ApiAuthController.getToken(),
                "Check that login success and as result we have token");
    }

    @Test
    public void userControllerApiTest() {
        Logger.step("1. Prepare (register) user and login");
        LoginUser loginUser = createRandomUserAndLogin();

        Logger.step("2. Get Self User Info using token from previous login user and check");
        ApiUserController apiUserController = new ApiUserController();
        PersonalUserData personalUserData = apiUserController.getSelfUser();

        Assert.assertEquals(personalUserData.getUsername(), loginUser.getUsername(),
                "Check user name contains name from loginUser");
        Assert.assertTrue(personalUserData.getId() > 0,
                "Check user id not 0");
        Assert.assertNull(personalUserData.getName(),
                "Check user name null");
        Assert.assertNull(personalUserData.getLastname(),
                "Check lastname null");

        Logger.step("3. Get Specific User By ID using self user data and check");
        PersonalUserData userDataByID = apiUserController.getSpecificUserByID(personalUserData.getId());
        Assert.assertEquals(userDataByID, personalUserData,
                "Check that user data by ID equals Self User Data");

        Logger.step("4. Modified user by ID, update and check");
        userDataByID.setName("Sveta");
        userDataByID.setLastname("Sveta from Odesa");
        PersonalUserData updateUserData = apiUserController.updateUserData(userDataByID);
        Assert.assertEquals(updateUserData, userDataByID,
                "Check that updated user equals modified user by ID");
    }

    @Test
    public void jobControllerApiTest() {
        Logger.step("1. Prepare (register) user and login");
        LoginUser loginUser = createRandomUserAndLogin();

        Logger.step("2. Get all Jobs");
        ApiJobController apiJobController = new ApiJobController();
        apiJobController.getAllJobs();

        Logger.step("3. Create new job and check");
        JobData newJobData = new JobData();
        newJobData.setTitle("New random Job #" + new Random().nextInt(99));
        newJobData.setDescription("New random Job with random price, created by Sveta, using acc: " + loginUser.getUsername());
        newJobData.setPrice(10 + new Random().nextInt(989));

        JobData createdJobData = apiJobController.createJob(newJobData);
        Assert.assertEquals(createdJobData.getTitle(), newJobData.getTitle(),
                "Check that title of updated job equals new job");
        Assert.assertEquals(createdJobData.getDescription(), newJobData.getDescription(),
                "Check that description of updated job equals new job");
        Assert.assertEquals(createdJobData.getPrice(), newJobData.getPrice(),
                "Check that rice of updated job equals new job");
        Assert.assertTrue(createdJobData.getId() > 0,
                "Check job id not 0");
        Assert.assertEquals(createdJobData.getNoOfComments(), 0,
                "Check number of comments equals 0");

        Logger.step("4. Get job by ID and check");
        JobData jobDataByID = apiJobController.getSpecificJobByID(createdJobData.getId());
        Assert.assertEquals(jobDataByID, createdJobData,
                "Check that job data by ID equals updated job");

        Logger.step("5. Delete Job by ID and check");
        boolean isDeleted = apiJobController.deleteJob(createdJobData.getId());
        Assert.assertTrue(isDeleted,
                "Check that job was deleted with message 'Job is deleted'");
    }

    @Test
    public void commentControllerApiTest() {
        Logger.step("1. Prepare (register) user and login, create job");
        LoginUser loginUser = createRandomUserAndLogin();
        JobData newJobData = new JobData();
        newJobData.setTitle("New random Job #" + new Random().nextInt(99));
        newJobData.setDescription("New random Job with random price, created by Sveta, using acc: " + loginUser.getUsername());
        newJobData.setPrice(10 + new Random().nextInt(989));
        JobData createdJob = new ApiJobController().createJob(newJobData);

        Logger.step("2. Create comment");
        ApiCommentController apiCommentController = new ApiCommentController();
        CommentData newComment = new CommentData();
        newComment.setMessage("My test comment created by Sveta");
        CommentData createdComment = apiCommentController.createComment(createdJob.getId(), newComment);

        Assert.assertEquals(createdComment.getMessage(), newComment.getMessage(),
                "Check that job data by ID equals updated job");
        Assert.assertTrue(createdComment.getId() > 0,
                "Check created comment ID not 0");

        Logger.step("3. Add one more comment and get all comments");
        CommentData newComment2 = new CommentData();
        newComment2.setId(1);
        newComment2.setMessage("My test comment 2 created by Sveta");
        apiCommentController.createComment(createdJob.getId(), newComment2);

        apiCommentController.getAllCommentsForSpecificJob(createdJob.getId());
    }

    private LoginUser createRandomUserAndLogin() {
        RegisterUser newRegisterUser = getRandomRegisterUser();
        ApiAuthController apiAuthController = registerRandomUserByApi(newRegisterUser);

        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(newRegisterUser.getUsername());
        loginUser.setPassword(newRegisterUser.getPassword());

        apiAuthController.login(loginUser);

        return loginUser;
    }

    private ApiAuthController registerRandomUserByApi(RegisterUser userForRegistration) {
        ApiAuthController apiAuthController = new ApiAuthController();
        apiAuthController.registerUser(userForRegistration);
        return apiAuthController;
    }

}
