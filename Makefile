BT=sbt

run:
	@$(BT) compile

demo:
	@$(BT) "run-main lolcode.LolDemo"

specific-test:
	@$(BT) "run-main lolcode.$(O)"

specific-test-input:
	@echo $(INPUT) | $(BT) "run-main lolcode.$(O)"

test:
	@$(BT) "test-only"

console:
	@$(BT) console

eclipse:
	@$(BT) "eclipse with-source=true"