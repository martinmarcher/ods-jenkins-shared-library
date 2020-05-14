package vars.test_helper

import groovy.json.JsonSlurper
import com.lesfurets.jenkins.unit.BasePipelineTest
import org.ods.component.IContext
import spock.lang.Specification

/**
 * A base class for Spock testing using the Jenkins Pipeline Unit testing framework (https://github.com/jenkinsci/JenkinsPipelineUnit)
 */
class PipelineSpockTestBase extends Specification {

  @Delegate
  BasePipelineTest basePipelineTest

  def setup() {
    // create instance of abstract class BasePipelineTest by creating an anonymous class
    basePipelineTest = new BasePipelineTest() {
      @Override
      void registerAllowedMethods() {
        super.registerAllowedMethods()
        helper.registerAllowedMethod('readJSON', [ Map ]) { Map args -> new JsonSlurper().parseText(args.text) }
      }
    }
    basePipelineTest.setUp()
  }

  protected String readResource(String name) {
    def classLoader = getClass().getClassLoader();
    def file = new File(classLoader.getResource(name).getFile());
    file.text
  }
}
