BT=sbt

run:
	@echo not implemented

temp-test:
	@$(BT) "run-main lolcode.LolTest"

test:
	@$(BT) "test-only"

console:
	$(BT) console

eclipse:
	$(BT) "eclipse with-source=true"