(ns ici-recorder.clojush-benchmark
  (:require [ici-recorder.clojush :refer [record-run record-generation]]
            [taoensso.timbre :as timbre]
            [ici-recorder.test-utils :as test-utils]
            [clojure.spec.gen :as gen]
            [criterium.core :as criterium]))


(defn -main []
  (clojure.spec.test/with-instrument-disabled
    (let [g (gen/generate test-utils/generation-gen)]
      ; (println "Ready...")
      ; (read-line)
      ; (println "Starting")
      ; (time
      ;   (record-generation
      ;     test-utils/generation-write-support
      ;     "test-uuid"
      ;     0
      ;     g))))))
      (criterium/with-progress-reporting
        (criterium/quick-bench
          (record-generation
            test-utils/generation-write-support
            "test-uuid"
            0
            g)
          :verbose)))))