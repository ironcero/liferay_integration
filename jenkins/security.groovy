#!groovy

import jenkins.model.*

import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule
import hudson.plugins.git.*;
import hudson.model.FreeStyleProject;
import hudson.triggers.SCMTrigger;
import hudson.util.Secret;
import javaposse.jobdsl.plugin.*;
import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.domains.Domain;
import com.cloudbees.plugins.credentials.SystemCredentialsProvider;
import jenkins.model.JenkinsLocationConfiguration;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition;

def instance = Jenkins.getInstance()

// Credentials
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin", "changeit")
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
instance.setAuthorizationStrategy(strategy)
instance.save()

Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)

// Jobs
// Master job
def scm = new GitSCM("https://github.com/ironcero/liferay_integration_test.git");
scm.branches = [new BranchSpec("*/master")];
workflowJob = new WorkflowJob(instance, "Master branch");
workflowJob.definition = new CpsScmFlowDefinition(scm, "configs/docker/Jenkinsfile");
gitTrigger = new SCMTrigger("H/1 * * * *");
workflowJob.addTrigger(gitTrigger)

instance.add(workflowJob, workflowJob.name);