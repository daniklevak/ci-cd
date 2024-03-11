job('NodeJS example') {
    scm {
        git('https://github.com/daniklevak/ci-cd.git', 'main') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL FUN LAB')
            node / gitConfigEmail('jenins-dsl@domain.com')
        }
    }
    triggers {
        scm('H/5 * * * *') // this job will pull every 5 minutes.
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        shell("""cd basics; npm install; tar -czvf ../myapp.tar.gz .""")
    }

    publishers {
        archiveArtifacts('*.tar.gz')
    }
}

