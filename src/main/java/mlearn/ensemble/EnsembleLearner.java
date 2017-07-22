package mlearn.ensemble;

/**
 * @author dy[jealousing@gmail.com] on 17-5-10.
 */
/**
 * https://stats.stackexchange.com/a/19232
 *
 * It actually boils down to one of the "3B" techniques: bagging, boosting or blending.
 *
 * In bagging, you train a lot of classifiers on different subsets of object and combine answers by average
 * for regression and voting for classification (there are some other options for more complex situations, but I'll skip it).
 * Vote proportion/variance can be interpreted as error approximation since the individual classifiers are usually considered independent.
 * RF is in fact a bagging ensemble.
 *
 * Boosting is a wider family of methods, however their main point is that you build next classifier on the residuals of the former,
 * this way (in theory) gradually increasing accuracy by highlighting more and more subtle interactions.
 * The predictions are thus usually combined by summing them up, something like calculating a value of a function in x by summing values of its Taylor series' elements for x.
 * Most popular versions are (Stochastic) Gradient Boosting (with nice mathematical foundation) and
 * AdaBoost (well known, in fact a specific case of GB). From a holistic perspective, decision tree is a boosting of trivial pivot classifiers.
 *
 * Blending is an idea of nesting classifiers, i.e. running one classifier on an information system made
 * of predictions of other classifiers. As so, it is a very variable Linkage and certainly not a defined
 * algorithm; may require a lot of objects (in most cases the "blender" classifier must be trained on a
 * set of objects which were not used to build the partial classifiers to avoid embarrassing overfit).
 * The predictions of partial classifiers are obviously combined by melding them into an information system which is predicted by the blender.
 */
public interface EnsembleLearner {
}
