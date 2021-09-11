(require 'cemerick.pomegranate.aether)
(cemerick.pomegranate.aether/register-wagon-factory!
  "http" #(org.apache.maven.wagon.providers.http.HttpWagon.))

(defproject isomnath/clovaluator "1.0.0"
  :description "Clovaluator - Domain Agnostic Rule Engine"
  :url "https://github.com/isomnath/clovaluator"
  :uberjar-name "clovaluator.jar"
  :java-source-paths ["src/com"]
  :dependencies [[selmer "1.12.40"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "2.3.1"]
                 [org.clojure/tools.logging "1.1.0"]
                 [commons-codec/commons-codec "1.15"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot      :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"
                                  "-XX:-OmitStackTraceInFastThrow"]}
             :test    {:java-source-paths ["src/com"]
                       :jvm-opts          ["-Dlog4j.configurationFile=resources/log4j2.test.xml"]}}
  :plugins [[lein-cloverage "1.2.2"]])
