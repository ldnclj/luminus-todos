(ns my-app.test.home-route
  (:require [my-app.routes.home :refer :all]
            [clojure.test :refer :all]))


(deftest "create todos"
         (delete-all)
         (let [todos (create-todo "buy milk")]
           (is (= 1 (count @todos)))
           (is (= "buy milk" (first @todos))))

         )
