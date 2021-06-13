(defproject isomnath/clovaluator "0.0.1"
  :description "Clovaluator - Core Library"
  :url "https://github.com/isomnath/clovaluator"
  :uberjar-name "clovaluator.jar"
  :java-source-paths ["src"]
  :dependencies [[selmer "1.12.40"]
                 [cheshire "5.10.0"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.logging "1.1.0"]
                 [org.apache.logging.log4j/log4j "2.14.1" :extension "pom"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot      :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"
                                  "-XX:-OmitStackTraceInFastThrow"]}}
  :plugins [[lein-cljfmt "0.7.0"]
            [lein-cloverage "1.2.2"]])
