# DMN + Quarkus example

## Description

A simple DMN service to evaluate a traffic violation.

Demonstrates DMN on Kogito capabilities, including REST interface code generation.

## Installing and Running

### Prerequisites

You will need:
  - Java 11+ installed
  - Environment variable JAVA_HOME set accordingly
  - Maven 3.6.2+ installed

When using native image compilation, you will also need:
  - [GraalVM 19.3.1](https://github.com/oracle/graal/releases/tag/vm-19.3.1) installed
  - Environment variable GRAALVM_HOME set accordingly
  - Note that GraalVM native image compilation typically requires other packages (glibc-devel, zlib-devel and gcc) to be installed too.  You also need 'native-image' installed in GraalVM (using 'gu install native-image'). Please refer to [GraalVM installation documentation](https://www.graalvm.org/docs/reference-manual/aot-compilation/#prerequisites) for more details.

### Compile and Run in Local Dev Mode

```
mvn clean compile quarkus:dev
```

### Package and Run in JVM mode

```
mvn clean package
java -jar target/dmn-quarkus-example-runner.jar
```

or on Windows

```
mvn clean package
java -jar target\dmn-quarkus-example-runner.jar
```

### Package and Run using Local Native Image
Note that this requires GRAALVM_HOME to point to a valid GraalVM installation

```
mvn clean package -Pnative
```

To run the generated native executable, generated in `target/`, execute

```
./target/dmn-quarkus-example-runner
```

Note: This does not yet work on Windows, GraalVM and Quarkus should be rolling out support for Windows soon.

## OpenAPI (Swagger) documentation
[Specification at swagger.io](https://swagger.io/docs/specification/about/)

You can take a look at the [OpenAPI definition](http://localhost:8080/openapi?format=json) - automatically generated and included in this service - to determine all available operations exposed by this service. For easy readability you can visualize the OpenAPI definition file using a UI tool like for example available [Swagger UI](https://editor.swagger.io).

In addition, various clients to interact with this service can be easily generated using this OpenAPI definition.

When running in either Quarkus Development or Native mode, we also leverage the [Quarkus OpenAPI extension](https://quarkus.io/guides/openapi-swaggerui#use-swagger-ui-for-development) that exposes [Swagger UI](http://localhost:8080/swagger-ui/) that you can use to look at available REST endpoints and send test requests.

---
# JBang Example Usage

## [Install JBang](https://www.jbang.dev/documentation/guide/latest/installation.html)

## Loan_approvals.xlsxをDMNに変換する。
```
jbang xls2dmn@kiegroup Loan_approvals.xlsx 
```

## 生成されたLoan_approvals.xlsx.dmnをコマンドラインで実行する。’jo’と’jq’も使う。
```
jo 'FICO Score'=765 'DTI Ratio'=0.1 'PITI Ratio'=0.1 | jbang dmn@kiegroup Loan_approvals.xlsx.dmn | jq
```
すると、こんな結果が返ってくる。
```
{
  "Loan Approval": "Approved",
  "PITI Ratio": 0.1,
  "DTI Ratio": 0.1,
  "DTI Rating": "Good",
  "PITI Rating": "Good",
  "FICO Score": 765
}
```
### Approvedか否かの結果だけ欲しい場合：
```
jo 'FICO Score'=765 'DTI Ratio'=0.1 'PITI Ratio'=0.1 | jbang dmn@kiegroup Loan_approvals.xlsx.dmn | jq .'"Loan Approval"'
```
すると、
```
"Approved"
```
と返ってくる。