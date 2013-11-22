BT=sbt

run:
	@echo not implemented

specific-test:
	@$(BT) "run-main lolcode.$(O)"

test:
	@$(BT) "test-only"

console:
	$(BT) console

eclipse:
	$(BT) "eclipse with-source=true"