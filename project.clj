(defproject pulsar-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [co.paralleluniverse/pulsar "0.6.2"]
                 [co.paralleluniverse/quasar-core "0.6.2"]]

  :manifest {"Premain-Class" "co.paralleluniverse.fibers.instrument.JavaAgent"
             "Agent-Class" "co.paralleluniverse.fibers.instrument.JavaAgent"
             "Can-Retransform-Classes" "true"
             "Can-Redefine-Classes" "true"}
  
  :java-agents [[co.paralleluniverse/quasar-core "0.6.2"]]

  :main pulsar-example.core
  :uberjar-name "pulsar-example.jar" 
  :aot [pulsar-example.core])

