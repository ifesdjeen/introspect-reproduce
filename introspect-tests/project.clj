(defproject introspect-tests "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :main introspect-tests.core
  :jvm-opts ["-server"
             "-javaagent:/Users/ifesdjeen/hackage/history/introspect-reproduce/target/introspect-0.1.0-SNAPSHOT-standalone.jar"
             "-noverify"]

  :dependencies [[org.clojure/clojure "1.6.0"]])
