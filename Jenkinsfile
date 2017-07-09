node("docker") {
    docker.withRegistry('<<https://hub.docker.com/>>', '<<sulinb>>') {
    
        git url: "<<https://github.com/badgerboy17/A05-FacebookLite>>", credentialsId: '<<bryce-github>>'
    
        sh "git rev-parse HEAD > .git/commit-id"
        def commit_id = readFile('.git/commit-id').trim()
        println commit_id
    
        stage "build"
        def app = docker.build "facebooklite"
    
        stage "publish"
        app.push 'master'
        app.push "${commit_id}"
    }
}
