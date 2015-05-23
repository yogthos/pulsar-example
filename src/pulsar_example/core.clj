(ns pulsar-example.core
  (:use [co.paralleluniverse.pulsar core actors])
  (:refer-clojure :exclude [promise await])
  (:gen-class))

(defsfn ping [n pong]
  (if (== n 0)
    (do
      (! pong :finished)
      (println "ping finished"))
    (do
      (! pong [:ping @self])
      (receive
        :pong (println "Ping received pong"))
      (recur (dec n) pong))))

(defsfn pong []
  (receive
    :finished (println "Pong finished")
    [:ping ping] (do
                   (println "Pong received ping")
                   (! ping :pong)
                   (recur))))

(defn -main []
  (let [a1 (spawn pong)
        b1 (spawn ping 3 a1)]
    :ok))
