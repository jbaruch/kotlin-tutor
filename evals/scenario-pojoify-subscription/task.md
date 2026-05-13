# Subscription Model Modernisation

## Problem Description

The billing team owns a `Subscription` value object that has been carrying the same shape since the codebase was ported from Java. The class lives at `inputs/Subscription.kt`. It holds the data fields a subscription needs — identifier, plan, status, billing period, renewal flag, timestamps — and has been carrying its own implementations of the standard object methods plus Java-bean style accessors since the port.

The team is modernising older value types to fit current project conventions. The `Subscription` class is the next in the queue. The change must preserve the field order and the externally-observable semantics — equality must still mean "same logical record," string representation must still surface every field, and callers constructing `Subscription` positionally must continue to compile.

## Output Specification

Write the modernised file to `output/Subscription.kt`. Every field that the original class exposed must remain exposed; the field order must match the original; the public surface (construction, equality, toString) must be functionally equivalent.
