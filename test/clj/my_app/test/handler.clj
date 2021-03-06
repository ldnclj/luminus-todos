(ns my-app.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [my-app.handler :refer :all]
            [my-app.middleware.formats :as formats]
            [muuntaja.core :as m]
            [mount.core :as mount]))

(defn parse-json [body]
  (m/decode formats/instance "application/json" body))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'my-app.config/env
                 #'my-app.handler/app)
    (f)))

(deftest test-app
  (testing "todos"
           (let [response (app (request :post "/todos" {:todo "buy milk" }))]
             (is (= 302 (response :status)))))

  (testing "should not save empty todo"
           (let [response (app (request :post "/todos" {:todo ""}))]
             (is (= 500 (response :status)))))

  (testing "should not save empty todo"
           (let [response (app (request :delete "/todos" {:index 0}))]
             (is (= 500 (response :status)))))


  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 404 (:status response))))))
