node {

    stage('SCM checkout'){
        git branch: 'np_change', url: 'https://github.com/meajt/SORMAS-Project.git'
    }
    stage('Compile-Package'){
        sh 'mvn clean install'
    }
        
}
